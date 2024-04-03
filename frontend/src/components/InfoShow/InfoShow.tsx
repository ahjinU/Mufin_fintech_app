'use client';

import { useState } from 'react';
import { Square2StackIcon } from '@heroicons/react/24/solid';

interface InfoShowProps {
  label?: string;
  text: string;
  copyIcon: boolean;
}

export default function InfoShow({ label, text, copyIcon }: InfoShowProps) {
  const [showSnackbar, setShowSnackbar] = useState(false);

  const handleCopyText = () => {
    navigator.clipboard.writeText(text).then(() => {
      setShowSnackbar(true);
      setTimeout(() => setShowSnackbar(false), 3000);
    });
  };

  const iconContent = copyIcon ? (
    <button
      onClick={handleCopyText}
      className="hover:bg-custom-light-gray p-1 rounded-[0.8rem]"
    >
      <Square2StackIcon className="size-[2rem] fill-custom-black" />
    </button>
  ) : null;

  return (
    <div className="flex flex-col gap-[0.4rem]">
      <label className="custom-medium-text">{label}</label>
      <div className="flex justify-between items-center border-b border-custom-medium-gray">
        <input
          readOnly
          type="text"
          value={text}
          className="h-[2.4rem] px-[0.4rem] py-[0.4rem] custom-semibold-text bg-transparent focus:outline-none"
        />
        {iconContent}
      </div>
      {showSnackbar && (
        <div
          className="fixed bottom-[8rem] left-1/2 transform -translate-x-1/2
          p-4 rounded-[0.8rem] custom-light-text text-custom-white bg-custom-black-with-opacity z-10"
        >
          클립보드에 복사되었습니다!
        </div>
      )}
    </div>
  );
}
