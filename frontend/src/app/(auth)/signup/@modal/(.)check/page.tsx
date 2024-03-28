'use client';

import { useRouter } from 'next/navigation';
import AlertConfirm from '@/components/AlertConfirmModal/AlertConfirmModal';
import useRegisterStore from '../../_store/store';
import { signUpParent, signUpChild } from '../../_apis/apis';
import { useState } from 'react';

export default function Check() {
  const [isOpen, setIsOpen] = useState(true);
  const router = useRouter();
  const { registerData } = useRegisterStore();
  const { name, gender, birth, address, address2, password } = registerData;

  const signUp = async () => {
    try {
      const fetchedData = await signUpParent(
        name,
        gender,
        birth,
        address,
        address2,
        password,
      );
      if (fetchedData.ok) {
        router.push('/signup/complete');
      } else {
        console.log('회원가입 토큰 잘못됨', fetchedData);
      }
    } catch (error) {
      console.error('회원가입 에러', error);
    }
  };

  const background = isOpen ? 'bg-custom-black-with-opacity' : '';

  return (
    <div
      className={`absolute top-0 left-0 size-full flex justify-center ${background}`}
    >
      <AlertConfirm
        handleClickOkay={signUp}
        handleClickNo={() => {
          router.back();
        }}
        isOpen={isOpen}
        text="회원가입을 진행하시겠어요?"
      />
    </div>
  );
}
