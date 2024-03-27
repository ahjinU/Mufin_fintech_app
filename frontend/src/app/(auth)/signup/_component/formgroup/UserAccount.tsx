'use client';

import { useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import Link from 'next/link';

export default function UserAccount() {
  const [message, setMessage] = useState('');
  const [name, setName] = useState('');

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput label="이메일" mode="ERROR" isMsg message={message}>
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="이메일을 입력해주세요"
            value={name}
            onChange={() => {}}
            setValue={() => {}}
          />
          <TinyButton label="중복 확인" onClick={() => {}} />
        </div>
      </ComplexInput>
      <ComplexInput label="비밀번호" mode="NONE">
        <Input
          placeholder="비밀번호를 입력해주세요"
          value={name}
          onChange={() => {}}
          setValue={() => {}}
        />
      </ComplexInput>
      <ComplexInput label="비밀번호 확인" mode="NONE">
        <Input
          placeholder="비밀번호를 한번 더 입력해주세요"
          value={name}
          onChange={() => {}}
          setValue={() => {}}
        />
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Link href="signup/check">
          <Button label="다음" mode="ACTIVE" />
        </Link>
      </div>
    </div>
  );
}
