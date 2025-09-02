export interface StagiaireDTO {
    utilisateur: Utilisateur;
    id: number;
    name: string | null;
    firstname: string | null;
    mail: string | null;
}

export interface Utilisateur {
    name_?: string;
    firstname_?: string;
    mail?: string;
}


export interface Stagiaire {
    id?: number;
    utilisateur: Utilisateur;
}

const API_URL = "http://localhost:8080/stagiaires";

export async function getAllStagiaires(): Promise<StagiaireDTO[]> {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error(`Erreur récupération stagiaires: ${response.status}`);
    return response.json();
}

export async function getStagiaireById(id: number): Promise<StagiaireDTO> {
    const response = await fetch(`${API_URL}/${id}`);
    if (!response.ok) throw new Error(`Stagiaire introuvable: ${response.status}`);
    return response.json();
}

export async function createStagiaire(stagiaire: Stagiaire): Promise<StagiaireDTO> {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(stagiaire),
    });
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erreur création stagiaire: ${response.status} - ${errorText}`);
    }
    return response.json();
}

export async function deleteStagiaire(id: number): Promise<void> {
    const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    if (!response.ok && response.status !== 204) throw new Error(`Erreur suppression: ${response.status}`);
}

