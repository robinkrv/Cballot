import React from "react";
import ContentBlock from "../components/ContentBlock";
import DropDown from "../components/DropDown";
import TextField from "../components/TextField";

const CreateFormation = () => {
    return (
        <ContentBlock>
            <DropDown title={"Formation :"} />
            <TextField title={"Nom de la session :"} type={"text"} id={"sessionName"} />
            <TextField title={"Date de debut :"} type={"date"} id={"startDtae"} />
            <TextField title={"Date de fin :"} type={"date"} id={"endDaate"} />
        </ContentBlock>
    );
};

export default CreateFormation;