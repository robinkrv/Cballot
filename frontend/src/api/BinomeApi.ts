export interface Binome {
  id?: number;
  nom: string;
  principalId: number;
  suppleantId: number;
  scrutinId: number;
}

const API_URL = "http://localhost:8080/binomes";

export async function createBinome (binome: Omit<Binome, "id">): Promise<Binome>{
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(binome),
        });
        if (!response.ok) throw new Error("Erreur HTTP");
        return response.json();
    } catch(error) {
        console.error("Erreur createBinome:", error);
        throw error;
    }
}

export async function fetchBinome(id: number): Promise<Binome> {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error("Erreur HTTP");
        return response.json();
    } catch (error) {
        console.error("Erreur fetchBinome:", error);
        throw error;
    }
}

export async function fetchBinomes(): Promise<Binome[]> {
     try {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error("Erreur HTTP"); 
    return response.json();                
  } catch (error) {
    console.error("Erreur fetchBinomes:", error); 
    throw error; 
  }
}

export async function deleteBinome(id: number): Promise<void> {
    try{
        const response =await fetch(`${API_URL}/${id}`, {method: "DELETE"});
        if(!response.ok) throw new Error("Erreur HTTP");
    } catch (error){
        console.error("Erreur deleteBinome:", error);
        throw error;
    }
}


