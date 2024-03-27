import { useState } from 'react';
import { Input } from '@/components';

export default function DateInput({
  name,
  onChange,
}: {
  name: 'year' | 'month' | 'day';
  onChange: (value: string) => void;
}) {
  const [inputValue, setInputValue] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setInputValue(value);
    onChange(value);
  };

  return (
    <div className="flex items-center gap-[0.4rem]">
      <Input
        placeholder={name === 'year' ? '1998' : name === 'month' ? '04' : '22'}
        name={name}
        value={inputValue}
        onChange={handleInputChange}
        width={name === 'year' ? 'w-[10.4rem]' : 'w-[6.4rem]'}
        reset={false}
      />
      <p className="custom-semibold-text text-custom-black">
        {name === 'year' ? '년' : name === 'month' ? '월' : '일'}
      </p>
    </div>
  );
}
