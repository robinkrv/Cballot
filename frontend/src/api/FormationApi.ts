
export interface Admin {
    id?: number;
    name?: string;
}

export interface Scrutin {
    id?: number;
    nom?: string;
 
}

export interface Stagiaire {
    id?: number;
    name?: string;
    firstname?: string;
    mail?: string;
  
}

export interface Session {
    id?: number;
    nom: string;
    dateDebut: string; 
    dateFin: string;
    formation?: Formation;
    stagiairesponse?: Stagiaire[];
    scrutins?: Scrutin[];
    admins?: Admin[];
}

export interface Formation {
    id?: number;
    nom: string;
    sessions?: Session[];
}


const API_URL_FORMATION = "http://localhost:8080/formation";

export async function getAllFormations(): Promise<Formation[]> {
    const response = await fetch(API_URL_FORMATION);
    if (!response.ok) throw new Error(`Erreur récupération formations: ${response.status}`);
    return response.json();
}

export async function getFormationById(id: number): Promise<Formation> {
    const response = await fetch(`${API_URL_FORMATION}/${id}`);
    if (!response.ok) throw new Error(`Formation introuvable: ${response.status}`);
    return response.json();
}

export async function createFormation(formation: Omit<Formation, "id" | "sessions">): Promise<Formation> {
    const response = await fetch(API_URL_FORMATION, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formation),
    });
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erreur création formation: ${response.status} - ${errorText}`);
    }
    return response.json();
}

export async function deleteFormation(id: number): Promise<void> {
    const response = await fetch(`${API_URL_FORMATION}/${id}`, { method: "DELETE" });
    if (!response.ok && response.status !== 204) throw new Error(`Erreur suppresponsesion: ${response.status}`);
}
