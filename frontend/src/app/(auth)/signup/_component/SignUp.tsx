'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { GuideText, ProgressBar } from '@/components';

import UserInfo from './formgroup/UserInfo';

type userInfoData = {
  name: string;
  gender: string;
  birth: string;
};

export default function SignUp() {
  const [state, setState] = useState('first');
  const [registerData, setRegisterData] = useState({});

  const router = useRouter();

  const first = {
    next: (data: userInfoData) => {
      setState('second');
      setRegisterData({
        ...registerData,
        name: data.name,
        gender: data.gender,
        birth: data.birth,
      });
    },
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <GuideText text="부모님 먼저 가입한 후 아이도 회원가입할 수 있어요!" />
      <ProgressBar barGage={100 / 3} />
      <UserInfo next={first.next} />
    </div>
  );
}
