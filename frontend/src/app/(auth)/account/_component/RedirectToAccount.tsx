'use client';

import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

export default function RedirectToSignUp() {
  const router = useRouter();

  useEffect(() => {
    router.replace('/account');
  }, [router]);

  return <></>;
}
