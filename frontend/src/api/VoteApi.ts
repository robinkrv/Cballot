export interface VoteDTO {
    uuid: string;       
    binomeId: number;  
}

export interface VoteResponse {
    message: string;   
    binomeId: number;   
}

export interface VoteResult {
    binomeId: number; 
    nomBinome: string;
    totalVotes: number;
}

const API_URL = "http://localhost:8080/api"; 

export async function submitVote(vote: VoteDTO): Promise<VoteResponse> {
  const response = await fetch(`${API_URL}/votes/submit`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vote),
  });
  if (!response.ok) throw new Error(await response.text());
  return response.json();
}

export async function getResults(): Promise<VoteResult[]> {
  const response = await fetch(`${API_URL}/votes/results`);
  if (!response.ok) throw new Error("Erreur récupération résultats");
  return response.json();
}
