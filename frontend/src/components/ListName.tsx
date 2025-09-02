import React, { useState, useEffect } from "react";
import { StagiaireDTO, Stagiaire, getAllStagiaires, createStagiaire, deleteStagiaire } from "../api/StagiaireApi";

const ListStagiaires: React.FC = () => {
  const [liste, setListe] = useState<StagiaireDTO[]>([]);
  const [name, setName] = useState("");
  const [firstname, setFirstname] = useState("");
  const [mail, setMail] = useState("");

  useEffect(() => {
    fetchStagiaires();
  }, []);

  const fetchStagiaires = async () => {
    try {
      const data = await getAllStagiaires();
      setListe(data);
    } catch (err: any) {
      console.error(err);
    }
  };

  const ajouter = async () => {
    if (!name.trim() || !firstname.trim() || !mail.trim()) {
      alert("Tous les champs sont obligatoires !");
      return;
    }

    const newStagiaire: Stagiaire = {
      utilisateur: { name, firstname, mail }
    };

    try {
      const created = await createStagiaire(newStagiaire);
      setListe(prev => [...prev, created]);
      setName("");
      setFirstname("");
      setMail("");
    } catch (err: any) {
      alert(`Impossible de créer le stagiaire: ${err.message}`);
    }
  };

  const supprimer = async (id: number) => {
    try {
      await deleteStagiaire(id);
      setListe(prev => prev.filter(s => s.id !== id));
    } catch (err: any) {
      alert(`Impossible de supprimer le stagiaire: ${err.message}`);
    }
  };

  return (
    <div className="container mt-3 mb-2 border border-dark ">
      {/* Formulaire */}
      <div className="d-flex">
        <input
          type="text"
          className="form-control border-0 border-end border-dark rounded-0"
          placeholder="Nom"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="text"
          className="form-control border-0 border-end border-dark rounded-0"
          placeholder="Prénom"
          value={firstname}
          onChange={(e) => setFirstname(e.target.value)}
        />
        <input
          type="email"
          className="form-control border-0 rounded-0"
          placeholder="Email"
          value={mail}
          onChange={(e) => setMail(e.target.value)}
        />
        <button className="btn fs-1 fw-bold" onClick={ajouter}>+</button>
      </div>

      {/* Liste */}
      <div className="card border-0 border-top border-dark rounded-0">
        <ul className="list-group list-group-flush">
          {liste.map((s) => (
            <li key={s.id} className="list-group-item d-flex justify-content-between align-items-center">
              <span>{s.name} {s.firstname} - {s.mail}</span>
              <button className="btn btn-sm fs-5 fw-bold" onClick={() => supprimer(s.id)}>X</button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ListStagiaires;
