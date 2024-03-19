'use client';

import { XCircleIcon } from '@heroicons/react/24/solid';
import { useRef, useState } from 'react';

interface InputProps {
  width?: string;
  height?: string;
  reset?: boolean;
  placeholder?: string;
  value?: string | number;
  setValue?: Function;
  onChange?: () => void;
}

export default function DealList({
  width = 'w-full',
  height = 'h-[4.4rem]',
  reset = true,
  placeholder,
  value,
  setValue,
  onChange,
  ...props
}: InputProps) {
  const [inputPlaceholder, setInputPlaceholder] = useState(placeholder || '');
  const [inputValue, setInputValue] = useState(value || '');
  const inputRef = useRef<HTMLInputElement>(null);

  const handleReset = () => {
    setInputValue('');
    inputRef.current?.focus();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setInputValue(newValue);
    onChange && onChange();
    setValue && setValue(newValue);
  };

  return (
    <div
      className={`${height} ${width} bg-custom-white border-custom-medium-gray  border-[0.1rem] 
      rounded-[0.8rem] p-[1.5rem] pr-[0.6rem] 
      focus:outline-none focus-within:border-custom-purple focus-within:ring-[0.05rem] focus-within:ring-custom-purple
      flex items-center justify-center z-999`}
      onClick={() => {
        inputRef.current?.setSelectionRange(
          inputRef.current?.value.length,
          inputRef.current?.value.length,
        );
      }}
    >
      <input
        className={`w-full outline-none  text-[1.6rem] custom-semibold-text text-custom-black ${
          typeof value === 'number' && 'text-right'
        }`}
        {...props}
        placeholder={inputPlaceholder}
        value={inputValue}
        ref={inputRef}
        onChange={handleChange}
        onFocus={() => setInputPlaceholder('')}
      />
      {reset && (
        <button onClick={handleReset}>
          <XCircleIcon className="h-[2.4rem] w-[2.4rem] text-custom-light-gray hover:text-custom-medium-gray mr-[0.8rem]" />
        </button>
      )}
    </div>
  );
}
