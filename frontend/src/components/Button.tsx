import React from 'react'

interface ButtonProps {
label: string;
onClick?: () => void;
}

const Button: React.FC<ButtonProps> = ({ label, onClick }) => {
  return (
    <button
      className="btn btn-primary rounded-pill px-4 py-2"
      onClick={onClick}
    >
      {label}
    </button>
  );
};

export default Button;