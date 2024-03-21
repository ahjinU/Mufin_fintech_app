'use client';

import { useRef, useEffect } from 'react';

export const MSWComponent = () => {
  let isInitiated = useRef<boolean>(false);

  useEffect(() => {
    if (typeof window !== 'undefined') {
      if (
        process.env.NEXT_PUBLIC_API_MOCKING === 'enabled' &&
        !isInitiated.current
      ) {
        isInitiated.current = true; // worker.start() 두 번 실행되는 거 막기
        console.log('Mock server is running on client side');
        require('@/mocks/browser');
      }
    }
  }, []);

  return null;
};
