'use client';

import { useSession } from 'next-auth/react';
import SignIn from './_component/SignIn';
import SignOut from './_component/SignOut';

export default function Page() {
  const { data: session } = useSession();

  // 여기 바꿔
  if (!session) {
    return <SignOut />;
  }
  return <SignIn />;
}
