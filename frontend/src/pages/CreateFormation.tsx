import React, { useEffect, useState } from "react";
import ContentBlock from "../components/ContentBlock";
import DropDown from "../components/DropDown";
import TextField from "../components/TextField";
import Button from "../components/Button";
import ListName from "../components/ListName";

import { type Formation, getAllFormations } from "../api/FormationApi";

const CreateFormation = () => {
    const [formations, setFormations] = useState<Formation[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

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

    if (loading) return <p>Chargement des formations...</p>;
    if (error) return <p>Erreur: {error}</p>;


    return (
        <ContentBlock>
            <h1>Creer une session</h1>
            <DropDown 
                title="Formation :" 
                items={formations.map(f => f.nom)} 
            />
            <TextField title={"Nom de la session :"} type={"text"} id={"sessionName"} />
            <TextField title={"Date de debut :"} type={"date"} id={"startDate"} />
            <TextField title={"Date de fin :"} type={"date"} id={"endDate"} />
            <h4>Liste d'éleves</h4>
            <ListName/>
            <Button label={"valider"} />
        </ContentBlock>
    );
};

export default CreateFormation;