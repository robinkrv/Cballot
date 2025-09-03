import React from "react";

interface TextFieldProps {
    id: string;
    title: string;
    type: string;
    value?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const TextField : React.FC<TextFieldProps> = ({ title, type, id, value, onChange }) => {
    return(
       <div className="mb-3 mt-3 text-start">
      <label htmlFor={id} className="form-label d-block text-start">
        {title}
      </label>
      <input
        type={type}
        className="form-control border-dark rounded-0"
        id={id}
        value={value}
        onChange={onChange}
      />
    </div>
    )
}
export default TextField;