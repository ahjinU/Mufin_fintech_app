'use client';

import { useSession } from 'next-auth/react';
import Main from './_component/Main';
import MainWithoutSignIn from './_component/MainWithoutSignIn';

export default function Home() {
  const { data: session } = useSession();

  return (
    <main className="min-h-screen flex flex-col items-center">
      {/* 여기 바꾸기 */}
      {!session ? <Main /> : <MainWithoutSignIn />}
    </main>
  );
}
