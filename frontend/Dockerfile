# --- STAGE 1: Der Build-Prozess mit Node (Vite erzeugt die Dateien) ---
FROM node:20-alpine AS build
WORKDIR /app

# Kopiere die Paket-Definitionen
COPY package*.json ./

# Installiere alle Abhängigkeiten als root (verhindert Permission Denied)
RUN npm ci

# Kopiere den restlichen Quellcode
COPY . .

# Vite baut das Projekt in den Ordner /app/dist
RUN npm run build

# --- STAGE 2: Der eigentliche Server (Nginx liefert nur noch aus) ---
FROM nginx:alpine

# Kopiere Ihre eigene nginx.conf aus dem vorherigen Schritt in den Container
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Kopiere die von Vite gebauten statischen Dateien in den Nginx-Webordner
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 8081
CMD ["nginx", "-g", "daemon off;"]