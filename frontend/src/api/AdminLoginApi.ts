export interface LoginDTO {
  email: string;
  password: string;
}

export interface Admin {
  id: number;
  email: string;
  name: string;
}

export async function login(dto: LoginDTO): Promise<Admin> {
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto),
  });

  if (!response.ok) {
    const error = await response.text();
    throw new Error(error);
  }

  const data: Admin = await response.json();
  return data;
}