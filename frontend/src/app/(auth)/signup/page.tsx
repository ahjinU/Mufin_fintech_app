'use client';

import { useSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import SignUp from './_component/SignUp';

export default function Page() {
  const { data: session } = useSession();
  const router = useRouter();

  if (session) {
    router.replace('/');
  }
  return <SignUp />;
}
