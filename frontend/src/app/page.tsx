'use client';

import { useSession } from 'next-auth/react';
import Main from './_component/Main';
import MainWithoutSignIn from './_component/MainWithoutSignIn';
import { NavBar } from '@/components';

export default function Home() {
  const { data: session } = useSession();

  return (
    <main className='relative'>
      <div className="min-h-screen flex flex-col items-center">
        {/* 여기 바꾸기 */}
        {!session ? <Main /> : <MainWithoutSignIn />}
      </div>
      <NavBar mode="CHILD" />
    </main>
  );
}
