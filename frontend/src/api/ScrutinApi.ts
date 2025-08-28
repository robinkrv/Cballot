export interface BinomeDTO {
  nom: string;
  scrutinId: number;
  principalId: number;
  suppleantId: number;
}

export interface ScrutinDTO {
  dateVote: Date; 
  tour: number;
  adminId: number;
  sessionId: number;
  binomes: BinomeDTO[];
}

export interface Scrutin {
  id: number;
  dateVote: string;
  tour: number;
  adminId: number;
  sessionId: number;
  binomes: BinomeDTO[];
}

const BASE_URL = "http://localhost:8080/scrutins";

export async function createScrutin(dto: ScrutinDTO): Promise<Scrutin> {
  const response = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto),
  });

  if (!response.ok) {
    throw new Error(await response.text());
  }

  return await response.json();
}

export async function getScrutins(): Promise<Scrutin[]> {
  const response = await fetch(BASE_URL);
  if (!response.ok) throw new Error("Erreur lors de la récupération des scrutins");
  return await response.json();
}

export async function getScrutinById(id: number): Promise<Scrutin> {
  const response = await fetch(`${BASE_URL}/${id}`);
  if (!response.ok) throw new Error("Scrutin introuvable");
  return await response.json();
}

export async function deleteScrutin(id: number): Promise<void> {
  const response = await fetch(`${BASE_URL}/${id}`, { method: "DELETE" });
  if (!response.ok) throw new Error("Erreur lors de la suppression du scrutin");
}
