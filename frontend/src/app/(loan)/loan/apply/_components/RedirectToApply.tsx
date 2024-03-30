'use client';

import { useRouter } from 'next/navigation';
import { useEffect } from 'react';

export default function RedirectToApply() {
  const router = useRouter();

  useEffect(() => {
    router.replace('/loan/apply');
  }, [router]);

  return <></>;
}
