export interface LienVoteRequest {
    destinataire: string;
    lienVote: string;
}

export interface ConfirmationRequest{
    destinataire: string;
}

const API_URL = "http://localhost:8080/mails";

export async function envoyerLienVote(request: LienVoteRequest): Promise<string> {
    const response = await fetch(`${API_URL}/lien-vote`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erreur envoi lien vote: ${response.status} - ${errorText}`);
    }

    return response.text(); 
}

export async function envoyerConfirmation(request: ConfirmationRequest): Promise<string> {
    const response = await fetch(`${API_URL}/confirmation`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(request),
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erreur envoi confirmation: ${response.status} - ${errorText}`);
    }

    return response.text(); 
}