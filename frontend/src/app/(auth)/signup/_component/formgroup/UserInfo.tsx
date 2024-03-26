'use client';

import { useState } from 'react';
import { ComplexInput, Input, Button } from '@/components';
import DateInput from '../input/DateInput';

export default function UserInfo() {
  const [message, setMessage] = useState('');
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');

  const goNext = async () => {
    console.log(gender, name);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput label="이름" mode="ERROR" isMsg message={message}>
        <Input
          placeholder="이름을 입력해주세요"
          value={name}
          onChange={() => {}}
          setValue={() => {}}
        />
      </ComplexInput>
      <ComplexInput label="성별" mode="NONE">
        <div className="flex gap-[0.8rem]">
          <Button
            label="남성"
            mode={gender === '남' ? 'SELECTED' : 'NOT_SELECTED'}
            onClick={() => setGender('남')}
          />
          <Button
            label="여성"
            mode={gender === '여' ? 'SELECTED' : 'NOT_SELECTED'}
            onClick={() => setGender('여')}
          />
        </div>
      </ComplexInput>
      <ComplexInput label="생년월일" mode="NONE">
        <div className="flex justify-between items-center">
          <DateInput name="year" onChange={() => {}} />
          <DateInput name="month" onChange={() => {}} />
          <DateInput name="day" onChange={() => {}} />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button label="다음" mode="ACTIVE" onClick={goNext} />
      </div>
    </div>
  );
}
