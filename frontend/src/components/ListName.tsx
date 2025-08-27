import React, { useState } from "react";

interface Personne {
  nom: string;
  prenom: string;
}

const ListName: React.FC = () => {
  const [nom, setNom] = useState("");
  const [prenom, setPrenom] = useState("");
  const [liste, setListe] = useState<Personne[]>([]);

  const ajouter = () => {
    if (nom.trim() && prenom.trim()) {
      setListe([...liste, { nom, prenom }]);
      setNom("");
      setPrenom("");
    }
  };

  const supprimer = (index: number) => {
    setListe(liste.filter((_, i) => i !== index));
  };

  return (
    <div className="container mt-3 mb-2 border border-dark ">
      {/* Formulaire en flex */}
      <div className="d-flex ">
        <input
          type="text"
          className="form-control border-0 border-end border-dark rounded-0"
          placeholder="Nom"
          value={nom}
          onChange={(e) => setNom(e.target.value)}
        />
        <input
          type="text"
          className="form-control border-0 rounded-0"
          placeholder="Prénom"
          value={prenom}
          onChange={(e) => setPrenom(e.target.value)}
        />
        <button className="btn fs-1 fw-bold" onClick={ajouter}>
          +
        </button>
      </div>

      {/* Liste */}
      <div className="card border-0 border-top border-dark rounded-0">
        <ul className="list-group list-group-flush">
          {liste.map((p, i) => (
            <li key={i} className="list-group-item d-flex justify-content-between align-items-center ">
              <span>{p.nom} {p.prenom}</span>
              <button 
                className="btn btn-sm fs-5 fw-bold"
                onClick={() => supprimer(i)}
              >
                X
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ListName;
