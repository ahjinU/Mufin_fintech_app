'use client';

import { useState } from 'react';
import { ComplexInput, Input, Button } from '@/components';
import DateInput from './DateInput';

type UserInfoPageProps = {
  previous: () => void;
  next: (data: any) => void;
  userInfo: {};
};

export default function UserInfo({ next }: UserInfoPageProps) {
  const [info, setInfo] = useState({ name: '', year: '', month: '', day: '' });
  const [gender, setGender] = useState('');
  const [message, setMessage] = useState('');

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setInfo({ ...info, [e.target.name]: e.target.value });
  };

  const goNext = () => {
    const data = {
      name: info.name,
      gender: gender,
      birth: info.year + '-' + info.month + '-' + info.day,
    };
    next(data);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput label="이름" mode="ERROR" isMsg message={message}>
        <Input
          placeholder="이름을 입력해주세요"
          name="name"
          onChange={onChangeInput}
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
          <DateInput name="year" onChange={onChangeInput} />
          <DateInput name="month" onChange={onChangeInput} />
          <DateInput name="day" onChange={onChangeInput} />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 inset-x-0 p-[1.2rem]">
        <Button label="다음" mode="ACTIVE" onClick={goNext} />
      </div>
    </div>
  );
}
