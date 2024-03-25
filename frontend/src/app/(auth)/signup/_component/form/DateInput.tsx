import { useState } from 'react';
import { Input } from '@/components';

export default function DateInput({ name, onChange }: { name: 'year' | 'month' | 'day', onChange: (value: string) => void }) {
  const [inputValue, setInputValue] = useState('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setInputValue(value);
    onChange(value); // 입력 값 변경 시 부모 컴포넌트로 전달
  };

  return (
    <div className="flex items-center gap-[0.4rem]">
      <Input
        placeholder={name === 'year' ? 'yyyy' : (name === 'month' ? 'mm' : 'dd')}
        name={name}
        value={inputValue}
        onChange={handleInputChange}
        width="w-[6.4rem]" // 너비는 필요에 따라 조정 가능
        reset={false}
      />
      <p className="custom-semibold-text text-custom-black">
        {name === 'year' ? '년' : (name === 'month' ? '월' : '일')}
      </p>
    </div>
  );
}
