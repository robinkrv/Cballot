export interface AutorisationDTO {
    uuid: string;
    aVote: boolean;
}

const API_URL = "http://localhost:8080/autorisations";

export async function genererAutorisation(stagiaireId: number): Promise<AutorisationDTO> {
    const response = await fetch(`${API_URL}/generer?stagiaireId=${stagiaireId}`, {
        method: "POST",
    });
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erreur génération autorisation: ${response.status} - ${errorText}`);
    }
    return response.json();
}

export async function getAutorisation(uuid: string): Promise<AutorisationDTO> {
    const response = await fetch(`${API_URL}/${uuid}`);
    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Autorisation introuvable: ${response.status} - ${errorText}`);
    }
    return response.json();
}

export async function deleteAutorisation(uuid: string): Promise<void> {
    const response = await fetch(`${API_URL}/${uuid}`, { method: "DELETE" });
    if (!response.ok && response.status !== 204) {
        const errorText = await response.text();
        throw new Error(`Erreur suppression autorisation: ${response.status} - ${errorText}`);
    }
}