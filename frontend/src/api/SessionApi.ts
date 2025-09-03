export interface SessionDTO {
  id?: number;
  nom: string;
  dateDebut: string;  
  dateFin: string;
  formationId?: number;
  stagiaireIds?: number[];
  scrutinIds?: number[];
  adminIds?: number[];
}

const API_URL = "http://localhost:8080/sessions"; 

export async function getSessions(): Promise<SessionDTO[]> {
  const res = await fetch(API_URL);
  if (!res.ok) throw new Error("Erreur lors du chargement des sessions");
  return res.json();
}

export async function getSession(id: number): Promise<SessionDTO> {
  const res = await fetch(`${API_URL}/${id}`);
  if (!res.ok) throw new Error(`Session ${id} introuvable`);
  return res.json();
}

export async function createSession(session: SessionDTO): Promise<SessionDTO> {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(session),
  });
  if (!res.ok) throw new Error("Erreur lors de la création de la session");
  return res.json();
}

export async function updateSession(id: number, session: SessionDTO): Promise<SessionDTO> {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(session),
  });
  if (!res.ok) throw new Error(`Erreur lors de la mise à jour de la session ${id}`);
  return res.json();
}

export async function deleteSession(id: number): Promise<void> {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
 if (!res.ok) throw new Error(`Erreur lors de la suppression de la session ${id}`);
}
