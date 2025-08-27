import React from "react";

interface TextFieldProps {
    id: string;
    title: string;
    type: string;
}

const TextField : React.FC<TextFieldProps> = ({ title, type , id}) => {
    return(
       <div className="mb-3 mt-3 text-start">
      <label htmlFor={id} className="form-label d-block text-start">
        {title}
      </label>
      <input
        type={type}
        className="form-control border-dark rounded-0"
        id={id}
      />
    </div>
    )
}
export default TextField;