'use client';

import { useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';

export default function UserContact() {
  const [message, setMessage] = useState('');
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');

  const goNext = async () => {
    console.log(gender, name);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput label="전화번호" mode="ERROR" isMsg message={message}>
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="전화번호를 입력해주세요"
            value={name}
            onChange={() => {}}
            setValue={() => {}}
          />
          <TinyButton label="중복 확인" onClick={() => {}} />
        </div>
      </ComplexInput>
      <ComplexInput label="주소" mode="NONE">
        <div className="flex flex-col gap-[0.6rem]">
          <div className="flex items-center gap-[1rem]">
            <Input
              placeholder="주소를 입력해주세요"
              value={name}
              onChange={() => {}}
              setValue={() => {}}
            />
            <TinyButton label="주소 찾기" onClick={() => {}} />
          </div>
          <Input
            placeholder="상세 주소를 입력해주세요"
            value={name}
            onChange={() => {}}
            setValue={() => {}}
          />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button label="다음" mode="ACTIVE" onClick={goNext} />
      </div>
    </div>
  );
}
