import React, { useEffect, useState } from "react";
import ContentBlock from "../components/ContentBlock";
import Button from "../components/Button";
import GestionBinomes from "../components/Binome";

interface StagiaireDTO {
    id: number;
    name: string | null;
    firstname: string | null;
    mail: string | null;
}


const Scrutin = () => {
 
    return (
        <ContentBlock>
            <h1>Planification du scrutin</h1>

            <div style={{ marginBottom: "1rem" }}>
                <label>Date et heure du vote:</label>
                <input
                    type="datetime-local"
                    value={voteDate}
                    onChange={e => setVoteDate(e.target.value)}
                    style={{ display: "block", padding: "0.5rem", marginTop: "0.25rem" }}
                />
            </div>

            <GestionBinomes />

            <h2>Ajouter un stagiaire</h2>
            <div style={{ marginBottom: "1rem" }}>
                <label>Nom:</label>
                <input type="text" value={name} onChange={e => setName(e.target.value)} />
            </div>
            <div style={{ marginBottom: "1rem" }}>
                <label>Prénom:</label>
                <input type="text" value={firstname} onChange={e => setFirstname(e.target.value)} />
            </div>
            <div style={{ marginBottom: "1rem" }}>
                <label>Email:</label>
                <input type="email" value={mail} onChange={e => setMail(e.target.value)} />
            </div>
            <Button label="Créer" onClick={handleAddStagiaire} />

            <h2>Liste des stagiaires</h2>
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {stagiaires.map(s => (
                        <tr key={s.id}>
                            <td>{s.id}</td>
                            <td>{s.name ?? "-"}</td>
                            <td>{s.firstname ?? "-"}</td>
                            <td>{s.mail ?? "-"}</td>
                            <td>
                                <Button label="Supprimer" onClick={() => handleDelete(s.id)} />
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <Button label="Valider" />
        </ContentBlock>
    );
};

export default Scrutin;
