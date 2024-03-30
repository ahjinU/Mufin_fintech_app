'use client';

import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

export default function RedirectToSavingsList() {
  const router = useRouter();

  useEffect(() => {
    router.push('/savingsList');
  }, [router]);

  return <></>;
}
