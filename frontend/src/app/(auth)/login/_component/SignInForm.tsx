'use client';

import { FormEvent, useState } from 'react';
import { signIn } from 'next-auth/react';
import { ComplexInput, Input, Button } from '@/components';

export default function SignInForm() {
  const [message, setMessage] = useState('');

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const formData = new FormData(e.target as HTMLFormElement);
    const email = formData.get('email') as string;
    const password = formData.get('password') as string;

    console.log(email, password); // 확인확인확인

    const result = await signIn('credentials', {
      redirect: false,
      email,
      password,
    });

    if (result === null) {
      setMessage('아이디와 비밀번호를 다시 확인해주세요!');
    }

    // 다음 페이지로 이동

    if (result && result.error) {
      console.error(result.error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-[1rem]">
      <ComplexInput label="이메일" mode="NONE">
        <Input placeholder="이메일을 입력해주세요" name="email" />
      </ComplexInput>
      <ComplexInput isMsg label="비밀번호" message={message} mode="ERROR">
        <Input placeholder="비밀번호를 입력해주세요" name="password" />
      </ComplexInput>
      <div className="my-[1.2rem]">
        <Button label="로그인" mode="ACTIVE" />
      </div>
    </form>
  );
}
