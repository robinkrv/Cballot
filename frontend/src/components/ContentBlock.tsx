import React from 'react';
import type { ReactNode } from 'react';

interface ContentBlockProps {
  children: ReactNode;
}

const ContentBlock: React.FC<ContentBlockProps> = ({ children }) => {
  return (
    <div
      className="bg-light rounded shadow p-4 mx-auto"
      style={{
        height: '70vh',
        width: '90%',   // largeur à ajuster selon ton besoin
        maxWidth: '800px', // optionnel pour pas que ça devienne trop large
      }}
    >
      {children}
    </div>
  );
};

export default ContentBlock;
