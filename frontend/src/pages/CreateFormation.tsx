import React, { useEffect, useState } from "react";
import ContentBlock from "../components/ContentBlock";
import DropDown from "../components/DropDown";
import TextField from "../components/TextField";
import Button from "../components/Button";
import ListName from "../components/ListName";

import { type Formation, getAllFormations } from "../api/FormationApi";
import { type SessionDTO, createSession } from "../api/SessionApi";

const CreateFormation = () => {
    const [formations, setFormations] = useState<Formation[]>([]);
     const [formationId, setFormationId] = useState<number | null>(null);
    const [nom, setNom] = useState("");
    const [dateDebut, setDateDebut] = useState("");
    const [dateFin, setDateFin] = useState("");
 
    useEffect(() => {
        async function fetchFormations() {
            try {
                const data = await getAllFormations();
                setFormations(data);
            } catch (err: any) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        }
        fetchFormations();
    }, []);
      async function handleSubmit() {
    if (!formationId) {
      alert("Choisis une formation !");
      return;
    }
    const newSession: SessionDTO = {
      nom,
      dateDebut,
      dateFin,
      formationId,
    };

    try {
      const created = await createSession(newSession);
      alert(`Session créée avec id: ${created.id}`);
    } catch (err: any) {
      alert("Erreur: " + err.message);
    }

      }
    return (
        <ContentBlock>
            <h1>Creer une session</h1>
            <DropDown
  title="Formation :"
  items={formations.map(f => f.nom)}
  value={formations.find(f => f.id === formationId)?.nom}
  onSelect={(nomChoisi) => {
    const f = formations.find(f => f.nom === nomChoisi);
    if (f) setFormationId(f.id);
  }}
/>

            <TextField title={"Nom de la session :"} type={"text"} id="sessionName" value={nom} onChange={(e) => setNom(e.target.value) }/>
            <TextField title={"Date de debut :"} type={"date"} id="startDate" value={dateDebut} onChange={(e) => setDateDebut(e.target.value) }/>
            <TextField title={"Date de fin :"} type={"date"} id="endDate" value={dateFin} onChange={(e) => setDateFin(e.target.value)}/>
            <h4>Liste d'éleves</h4>
            <ListName/>
            <Button label={"valider"} onClick={handleSubmit} />
        </ContentBlock>
    );
};

export default CreateFormation;