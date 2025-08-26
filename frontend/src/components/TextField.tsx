import React from "react";

interface TextFieldProps {
    id: string;
    title: string;
    type: string;
}

const TextField : React.FC<TextFieldProps> = ({ title, type , id}) => {
    return(
        <div className="form-group">
            <label htmlFor={title} className="mb-2 mt-3"> {title}</label>
            <input type={type} className="form-control" id={id} />
        </div>
    )
}
export default TextField;