import React, { useState, useEffect } from 'react';
const api_url = import.meta.env.VITE_API_URL 

export default function AusschreibungGenerator() {
  // 1. Vereins- & Event-Stammdaten
  const [eventData, setEventData] = useState({
    vereinsname: '',
    titel: '',
    datumVon: '',
    datumBis: '',
    ort: '',
    startgeld: '',
    anmeldeschluss: '',
    adresse: '',
    ansprechpartner: '',
    email: '',
    hinweiseSpO: true, 
    haftungsausschluss: true
  });

  // 2. Zustand für MySQL-Daten aus dem Backend
  const [verbaende, setVerbaende] = useState([]);
  const [disziplinen, setDisziplinen] = useState([]);
  const [altersklassen, setAltersklassen] = useState([]);

  // 3. Auswahl des Vereins
  const [selectedVerband, setSelectedVerband] = useState('');
  const [selectedDisziplin, setSelectedDisziplin] = useState('');
  const [selectedAltersklassen, setSelectedAltersklassen] = useState([]);

  // Ladezustände
  const [loadingDisziplinen, setLoadingDisziplinen] = useState(false);
  const [loadingAltersklassen, setLoadingAltersklassen] = useState(false);

  // Verbände beim Start laden (aus MySQL über Spring Boot)
  useEffect(() => {
    fetch(`${api_url}/api/verbaende`)
      .then((res) => res.json())
      .then((data) => setVerbaende(data))
      .catch((err) => console.error('Fehler beim Laden der Verbände:', err));
  }, []);

  // Disziplinen nach Verband nachladen
  const handleVerbandChange = (e) => {
    const verband = e.target.value;
    setSelectedVerband(verband);
    setSelectedDisziplin('');
    setDisziplinen([]);
    setSelectedAltersklassen([]);

    if (verband) {
      setLoadingDisziplinen(true);
      fetch(`${api_url}/api/disziplinen?verband=${encodeURIComponent(verband)}`)
        .then((res) => res.json())
        .then((data) => {
          setDisziplinen(data);
          setLoadingDisziplinen(false);
        });
    }
  };

  // Altersklassen nach Disziplin nachladen
  const handleDisziplinChange = (e) => {
    const disziplinId = e.target.value;
    setSelectedDisziplin(disziplinId);
    setSelectedAltersklassen([]);

    if (disziplinId) {
      setLoadingAltersklassen(true);
      fetch(`${api_url}/api/altersklassen?disziplinId=${encodeURIComponent(disziplinId)}`)
        .then((res) => res.json())
        .then((data) => {
          setAltersklassen(data);
          setLoadingAltersklassen(false);
        });
    }
  };

  // Checkbox-Handler für Altersklassen
  const handleAltersklasseToggle = (id) => {
    setSelectedAltersklassen((prev) =>
      prev.includes(id) ? prev.filter((item) => item !== id) : [...prev, id]
    );
  };

  // Text-Eingaben handhaben
  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setEventData((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  // Ausschreibung generieren / absenden
  const handleSubmit = (e) => {
    e.preventDefault();
    

    const ausschreibungPayload = {
      ...eventData,
      verband: selectedVerband,
      disziplin: selectedDisziplin,
      angeboteneAltersklassen: selectedAltersklassen
    };
    
    console.log('Erstelle Ausschreibung:', ausschreibungPayload);

    // POST an Spring Boot -> Speichern in MySQL & PDF-Generierung anstoßen
    fetch(`${api_url}/api/getpdf`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(ausschreibungPayload)
    })
      .then((res) => {
        // Falls der Server einen Fehler (z.B. 500) wirft, fangen wir ihn hier ab
        if (!res.ok) {
          throw new Error(`Server-Fehler: ${res.status}`);
        }
        return res.blob(); // Wandelt die Antwort in ein Binär-Objekt um
      })
      .then((blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.style.display = 'none';
        a.href = url;
        const sichererTitel = ('TestTitel');
        a.download = `${sichererTitel}.pdf`;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      })
      .catch((err) => {
        console.error('Fehler beim PDF-Download:', err);
        alert('Der Download ist fehlgeschlagen: ' + err.message);
      });
  };

  return (
    <form className="ausschreibung-form" onSubmit={handleSubmit}>
      <h2>Ausschreibungs-Generator für Schützenvereine</h2>

      {/* ABSCHNITT 1: Vereins- & Eventdaten */}
      <fieldset>
        <legend>1. Angaben zum Verein & Wettkampf</legend>
        
        <div className="form-group">
          <label>Ausrichtender Verein:</label>
          <input
            type="text"
            name="vereinsname"
            placeholder="z. B. SV Musterstadt 1920 e.V."
            value={eventData.vereinsname}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Titel der Veranstaltung:</label>
          <input
            type="text"
            name="titel"
            placeholder="z. B. 45. Pokalschießen 2026"
            value={eventData.titel}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group-inline">
          <div>
            <label>Wettkampf Datum von:</label>
            <input
              type="date"
              name="datumVon"
              value={eventData.datumVon}
              onChange={handleInputChange}
              required
            />
          </div>
          <div>
            <label>bis:</label>
            <input
              type="date"
              name="datumBis"
              value={eventData.datumBis}
              onChange={handleInputChange}
            />
          </div>
        </div>

        <div className="form-group">
          <label>Anmeldeschluss:</label>
          <input
            type="date"
            name="anmeldeschluss"
            value={eventData.anmeldeschluss}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Adresse:</label>
          <input
            type="text"
            name="adresse"
            placeholder="z. B. Sportweg 1"
            value={eventData.adresse}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Ort:</label>
          <input
            type="text"
            name="ort"
            placeholder="z. B. 12345 Musterstadt"
            value={eventData.ort}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Startgeld (in € / Serie):</label>
          <input
            type="text"
            name="startgeld"
            placeholder="z. B. 8,00 €"
            value={eventData.startgeld}
            onChange={handleInputChange}
          />
        </div>
      </fieldset>

      {/* ABSCHNITT 2: Verband & Disziplin */}
      <fieldset>
        <legend>2. Sportliche Zuordnung</legend>

        <div className="form-group">
          <label>Regelwerk / Verband:</label>
          <select value={selectedVerband} onChange={handleVerbandChange} required>
            <option value="">-- Verband wählen --</option>
            {verbaende.map((verband) => (
              <option key={`${verband.kuerzel}`} value={`${verband.kuerzel}`}>
                {`${verband.kuerzel} ${verband.name}`}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label>Auszuschreibende Disziplin:</label>
          <select
            style={{ maxWidth: '40vw', textOverflow: 'ellipsis', overflow: 'hidden', whiteSpace: 'nowrap' }}
            value={selectedDisziplin}
            onChange={handleDisziplinChange}
            disabled={!selectedVerband || loadingDisziplinen}
            required
          >
            <option value="">
              {loadingDisziplinen ? 'Lade Disziplinen...' : '-- Erst Verband wählen --'}
            </option>
            {disziplinen.map((disziplin) => (
              <option key={disziplin.id} value={disziplin.id} >
                {disziplin.kennziffer} ({disziplin.bezeichnung}: {disziplin.waffenart} - {disziplin.distanz}m)
              </option>
            ))}
          </select>
        </div>
      </fieldset>

      {/* ABSCHNITT 3: Altersklassen festlegen */}
      {selectedDisziplin && (
        <fieldset>
          <legend>3. Zugelassene Altersklassen festlegen</legend>
          {loadingAltersklassen ? (
            <p>Lade Altersklassen...</p>
          ) : (
            <table className="checkbox-table" style={{ width: '100%', borderCollapse: 'collapse' }}>
  <tbody>
    {/* Wir springen in 2er-Schritten durch das Array */}
    {altersklassen.reduce((rows, ak, index) => {
      if (index % 2 === 0) {
        // Starte eine neue Zeile für das erste Element des Paars
        rows.push([ak]);
      } else {
        // Füge das zweite Element zur aktuellen Zeile hinzu
        rows[rows.length - 1].push(ak);
      }
      return rows;
    }, []).map((pair, rowIndex) => (
      <tr key={rowIndex}>
        {pair.map((ak) => {
          const keyId = ak.id || ak.kennziffer;
          return (
            <td key={keyId} style={{ width: '50%', padding: '8px', verticalAlign: 'top' }}>
              <label className="checkbox-item" style={{ display: 'block', cursor: 'pointer' }}>
                <input
                  type="checkbox"
                  checked={selectedAltersklassen.includes(keyId)}
                  onChange={() => handleAltersklasseToggle(keyId)}
                  style={{ marginRight: '8px' }}
                />
                <strong>{ak.beschreibung}</strong> {ak.kennziffer} ({ak.geschlecht}, Alter {ak.age})
              </label>
            </td>
          );
        })}
        {/* Falls die Liste ungerade ist, füllen wir die zweite Zelle leer auf */}
        {pair.length === 1 && <td style={{ width: '50%' }}></td>}
      </tr>
    ))}
  </tbody>
</table>
          )}
        </fieldset>
      )}

      {/* ABSCHNITT 4: Statische/Rechtliche Bedingungen & Kontakt */}
      <fieldset>
        <legend>4. Rahmenbedingungen & Kontakt für die Ausschreibung</legend>

        <div className="form-group">
          <label>Ansprechpartner / Wettkampfleiter:</label>
          <input
            type="text"
            name="ansprechpartner"
            placeholder="z. B. Max Mustermann (Sportleiter)"
            value={eventData.ansprechpartner}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Kontakt E-Mail für Anmeldungen:</label>
          <input
            type="email"
            name="email"
            value={eventData.email}
            onChange={handleInputChange}
            required
          />
        </div>

        <div className="form-group checkbox-single">
          <label>
            <input
              type="checkbox"
              name="hinweiseSpO"
              checked={eventData.hinweiseSpO}
              onChange={handleInputChange}
            />
            Standard-Hinweis einfügen: <em>„Der Wettkampf wird nach der Sportordnung des jeweiligen Verbandes ausgetragen.“</em>
          </label>
        </div>

        <div className="form-group checkbox-single">
          <label>
            <input
              type="checkbox"
              name="haftungsausschluss"
              checked={eventData.haftungsausschluss}
              onChange={handleInputChange}
            />
            Standard-Haftungsausschluss des Veranstalters aufnehmen.
          </label>
        </div>
      </fieldset>

      <button onClick={handleSubmit} className="submit-btn">
        📄 Ausschreibung generieren (PDF / Vorschau)
      </button>
    </form>
  );
}