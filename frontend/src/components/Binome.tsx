import React, { useState } from "react";

interface Binome {
  nom: string;
  personne1: string;
  personne2: string;
}

const GestionBinomes: React.FC = () => {
  const [binomes, setBinomes] = useState<Binome[]>([
    { nom: "", personne1: "", personne2: "" },
  ]);

  const ajouterBinome = () => {
    setBinomes([...binomes, { nom: "", personne1: "", personne2: "" }]);
  };

  const supprimerBinome = (index: number) => {
    setBinomes(binomes.filter((_, i) => i !== index));
  };

  const updateBinome = (
    index: number,
    field: keyof Binome,
    value: string
  ) => {
    const newBinomes = [...binomes];
    newBinomes[index][field] = value;
    setBinomes(newBinomes);
  };

  return (
    <div className="container p-3">
      {binomes.map((b, i) => (
        <div key={i} className="mb-4">
          {/* Ligne Nom binôme + bouton supprimer */}
          <div className="row align-items-center mb-2">
                <div className="col">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Nom binôme..."
                    value={b.nom}
                    onChange={(e) => updateBinome(i, "nom", e.target.value)}
                  />
                </div>
                <div className="col-auto">
                  <button
                    className="btn btn-danger"
                    onClick={() => supprimerBinome(i)}
                  >
                    X
                  </button>
                </div>
            </div>


          <div className="row">
            <div className="col-md-6 mb-2">
              <input
                type="text"
                className="form-control"
                placeholder="Personne 1..."
                value={b.personne1}
                onChange={(e) => updateBinome(i, "personne1", e.target.value)}
              />
            </div>
            <div className="col-md-6 mb-2">
              <input
                type="text"
                className="form-control"
                placeholder="Personne 2..."
                value={b.personne2}
                onChange={(e) => updateBinome(i, "personne2", e.target.value)}
              />
            </div>
          </div>
        </div>
      ))}

      <div className="text-end">
        <button className="btn fs-1 fw-bold" onClick={ajouterBinome}>
          +
        </button>
      </div>
    </div>
  );
};

export default GestionBinomes;
