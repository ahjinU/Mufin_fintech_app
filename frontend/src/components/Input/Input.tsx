'use client';

import { XCircleIcon } from '@heroicons/react/24/solid';
import { useRef, useState } from 'react';

interface InputProps {
  width?: string;
  height?: string;
  reset?: boolean;
  placeholder?: string;
  name?: string;
  value?: string | number;
  disabled?: boolean;
  setValue?: Function;
  isRight?: boolean;
  type?: string;
  regExp?: RegExp;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function Input({
  width = 'w-full',
  height = 'h-[4.4rem]',
  reset = true,
  placeholder,
  name,
  value,
  setValue,
  onChange,
  disabled,
  isRight,
  type = 'text',
  regExp,
  ...props
}: InputProps) {
  const [inputPlaceholder, setInputPlaceholder] = useState(placeholder || '');
  const [inputValue, setInputValue] = useState(
    value !== undefined ? value : '',
  );
  const inputRef = useRef<HTMLInputElement>(null);

  const handleReset = () => {
    setValue && setValue('');
    inputRef.current?.focus();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    onChange && onChange(e);
    if (regExp) {
      if (regExp.test(newValue)) {
        setValue ? setValue(newValue) : setInputValue(newValue);
      }
    } else {
      setValue ? setValue(newValue) : setInputValue(newValue);
    }
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
        className={`w-full outline-none text-[1.6rem] custom-semibold-text text-custom-black ${
          isRight && 'text-right'
        } ${disabled === true && 'bg-custom-white'}`}
        {...props}
        placeholder={inputPlaceholder}
        name={name}
        value={value || inputValue}
        type={type}
        ref={inputRef}
        disabled={disabled}
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
