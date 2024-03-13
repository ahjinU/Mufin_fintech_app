'use client';

import { XCircleIcon } from '@heroicons/react/24/solid';
import { useRef, useState } from 'react';

interface InputProps{
  width?: string;
  reset?: boolean;
  placeholder?: string;
  value?: string;
  setValue?: ( value : string ) => void;
}

export default function Input({ width , reset = true, placeholder, value, setValue, ...props } : InputProps ){

  const [ inputPlaceholder, setInputPlaceholder ] = useState( placeholder || '내용을 입력해주세요' );
  const [ inputValue, setInputValue ] = useState( value || '' );
  const inputRef = useRef<HTMLInputElement>(null);

  const handleReset = () => {
    setInputValue('');
    inputRef.current?.focus();
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = e.target.value;
    setInputValue( newValue );
    setValue && setValue( newValue );
  };

  return(
    <div
      className= {`${width ? `w-[${width}]` : 'w-full'}  bg-custom-white h-[4.4rem] border border-custom-medium-gray 
      rounded-[0.8rem] p-[1.5rem] pr-[1rem]  border-[0.1rem]
      focus:outline-none focus-within:border-custom-purple focus-within:ring-[0.05rem] focus-within:ring-custom-purple
      text-[1.6rem] custom-semibold-text flex items-center justify-center`}
    >
      <input 
        className= 'w-full outline-none text-[1.6rem] custom-semibold-text'
        { ...props }
        placeholder = { inputPlaceholder }
        value= { inputValue }
        ref={ inputRef }
        onChange= { handleChange }
        onFocus= {() => setInputPlaceholder('')}
      />
        {
          reset && 
          <button onClick= { handleReset }>
            <XCircleIcon className= 'h-[2.4rem] w-[2.4rem] text-custom-light-gray hover:text-custom-medium-gray'/>
          </button>
        }
      </div>
  )
}