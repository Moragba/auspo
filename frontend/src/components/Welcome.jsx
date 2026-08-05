function Welcome({ onStart }) {
  return (
    <div className="welcome-container">
      <h1>Willkommen bei Auspo</h1>
      <p>
        Der digitale Auschreibungshelfer für Vereine.
      </p>
      <button onClick={onStart}>Jetzt starten</button>
      
    </div>
  );
}
export default Welcome;