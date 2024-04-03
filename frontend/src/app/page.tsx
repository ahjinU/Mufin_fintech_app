'use client';

import { useSession } from 'next-auth/react';
import Main from './_component/Main';
import MainWithoutSignIn from './_component/MainWithoutSignIn';

export default function Home() {
  const { data: session } = useSession();

  return (
    <main>
      <div className="flex flex-col">
        {session ? <Main /> : <MainWithoutSignIn />}
      </div>
    </main>
  );
}
