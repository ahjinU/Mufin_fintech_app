'use client';

import { useSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import useUserStore from '../../_store/store';
import SignUp from './_component/SignUp';

export default function Page() {
  const { data: session } = useSession();
  const { userData } = useUserStore();
  const router = useRouter();

  if (session) {
    router.replace('/');
  } else {
    return <SignUp forParent={!userData.isParent} />;
  }
}
