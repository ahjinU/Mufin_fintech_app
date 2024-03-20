'use client';

import { useRef, useEffect } from 'react';

export const MswComponent = () => {
  let isInitiated = useRef<boolean>(false);

  useEffect(() => {
    if (process.env.NODE_ENV === 'development' && !isInitiated.current) {
      isInitiated.current = true; // server.listen()이나 worker.start() 두 번 실행되는 거 막기

      if (typeof window === 'undefined') {
        (async () => {
          const { server } = await import('@/mocks/server');
          server.listen();
        })();
      } else {
        (async () => {
          const { worker } = await import('@/mocks/browser');
          worker.start();
        })();
      }
    }
  }, []);

  return null;
};
