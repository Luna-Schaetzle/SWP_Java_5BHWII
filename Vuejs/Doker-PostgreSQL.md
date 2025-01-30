# Docker 

Damit deine PostgreSQL-Daten in einem Docker-Container dauerhaft gespeichert werden (auch wenn der Container gestoppt oder gelöscht wird), musst du ein **volumes** verwenden. Volumes erlauben es, Daten auf deinem Host-System zu speichern, anstatt sie nur im Container zu halten.

### **Dauerhaftes Speichern von PostgreSQL-Daten**

#### **1. Volumes mit Docker CLI**
Du kannst ein Volume explizit angeben, wenn du den Container startest.

```bash
docker run --name postgres-container \
  -e POSTGRES_USER=mein_benutzer \
  -e POSTGRES_PASSWORD=mein_passwort \
  -e POSTGRES_DB=meine_datenbank \
  -p 5432:5432 \
  -v /pfad/auf/dem/host:/var/lib/postgresql/data \
  -d postgres
```

#### **Erklärung:**
- `-v /pfad/auf/dem/host:/var/lib/postgresql/data`: Verknüpft den Ordner auf deinem Host (`/pfad/auf/dem/host`) mit dem Datenverzeichnis des PostgreSQL-Containers (`/var/lib/postgresql/data`).
- PostgreSQL speichert nun alle Daten auf deinem Host-System. Auch wenn der Container gelöscht wird, bleiben die Daten erhalten.

#### **Beispiel mit absoluten Pfaden:**
Angenommen, du möchtest die Daten in `/home/benutzer/postgres-data` speichern:
```bash
docker run --name postgres-container \
  -e POSTGRES_USER=mein_benutzer \
  -e POSTGRES_PASSWORD=mein_passwort \
  -e POSTGRES_DB=meine_datenbank \
  -p 5432:5432 \
  -v /home/benutzer/postgres-data:/var/lib/postgresql/data \
  -d postgres
```

---

#### **2. Volumes mit benannten Docker-Volumes**
Du kannst auch ein benanntes Docker-Volume verwenden, um Daten zu speichern:

1. Erstelle ein Docker-Volume:
   ```bash
   sudo docker volume create postgres_data
   ```

2. Starte den Container mit diesem Volume:
   ```bash
   sudo docker run --name postgres-container \
     -e POSTGRES_USER=root \
     -e POSTGRES_PASSWORD=root \
     -e POSTGRES_DB=first_postgress \
     -p 5432:5432 \
     -v postgres_data:/var/lib/postgresql/data \
     -d postgres
   ```x

#### **Erklärung:**
- `postgres_data`: Das benannte Docker-Volume, das automatisch auf deinem System verwaltet wird.
- Auch wenn der Container gelöscht wird, bleibt das Volume erhalten. Du kannst es später erneut mit einem neuen Container verwenden.

---

#### **3. Mit Docker Compose**

Wenn du Docker Compose nutzt, kannst du die Volumes direkt in der `docker-compose.yml` definieren:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres
    container_name: postgres-container
    environment:
      POSTGRES_USER: mein_benutzer
      POSTGRES_PASSWORD: mein_passwort
      POSTGRES_DB: meine_datenbank
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

Starte den Container:
```bash
docker-compose up -d
```

---

### **Daten überprüfen**
Nach der Einrichtung kannst du prüfen, ob die Daten korrekt gespeichert werden:

1. **Nachsehen, ob das Volume existiert:**
   ```bash
   docker volume ls
   ```

2. **Auf den Volume-Inhalt zugreifen (falls nötig):**
   ```bash
   docker volume inspect postgres_data
   ```

3. **Host-Daten überprüfen:**
   Wenn du ein lokales Verzeichnis verwendest, navigiere zu dem angegebenen Verzeichnis (`/pfad/auf/dem/host`), um die gespeicherten Daten zu sehen.

---

Mit diesen Methoden bleiben deine PostgreSQL-Daten dauerhaft gespeichert!



Um deinen Docker-Container sicher zu stoppen, seine Daten zu speichern und später wieder aufzurufen, kannst du die folgenden Schritte befolgen:

---

### **1. Docker-Container stoppen**
Um den laufenden Container zu stoppen, verwende:
```bash
sudo docker stop postgres-container
```
Dieser Befehl beendet den Container sauber, ohne ihn zu löschen.

---

### **2. Daten sind automatisch gespeichert**
Durch die Angabe des Volumes `-v postgres_data:/var/lib/postgresql/data` werden die PostgreSQL-Daten im Volume `postgres_data` gespeichert. Selbst wenn der Container gestoppt oder gelöscht wird, bleiben die Daten erhalten.

---

### **3. Container neu starten**
Wenn du den Container später wieder starten möchtest:
```bash
sudo docker start postgres-container
```

Der Container läuft dann mit allen vorher gespeicherten Daten weiter.

---

### **4. Container entfernen, aber Daten behalten**
Falls du den Container löschen möchtest, aber das Volume mit den Daten behalten willst:
1. Entferne nur den Container:
   ```bash
   sudo docker rm postgres-container
   ```

2. Starte einen neuen Container mit dem gleichen Volume:
   ```bash
   sudo docker run --name postgres-container \
     -e POSTGRES_USER=root \
     -e POSTGRES_PASSWORD=root \
     -e POSTGRES_DB=first_postgress \
     -p 5432:5432 \
     -v postgres_data:/var/lib/postgresql/data \
     -d postgres
   ```

Die gespeicherten Daten aus dem Volume `postgres_data` werden wiederhergestellt.

---

### **5. Daten prüfen**
Um sicherzustellen, dass das Volume existiert:
```bash
sudo docker volume ls
```

Um den Inhalt des Volumes zu inspizieren:
```bash
sudo docker inspect postgres_data
```

---

### **Zusammenfassung:**
- **Stoppen:** `sudo docker stop postgres-container`
- **Starten:** `sudo docker start postgres-container`
- **Speichern der Daten:** Das Volume `postgres_data` speichert automatisch alle Daten.
- **Neu erstellen (mit Daten):** Starte einen neuen Container und verknüpfe das bestehende Volume erneut.

Mit diesen Schritten kannst du deinen PostgreSQL-Container einfach verwalten und sicherstellen, dass keine Daten verloren gehen.


POStgrest: 

docker run --name SWP_postgrest \
  --network swp \
  -e PGRST_DB_URI="postgres://root:root@postgres-container:5432/mydb" \
  -e PGRST_DB_SCHEMA="public" \
  -e PGRST_DB_ANON_ROLE="anon" \
  -p 3000:3000 \
  -d postgrest/postgrest
