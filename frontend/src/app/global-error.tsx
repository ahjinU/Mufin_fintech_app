'use client';

import dynamic from 'next/dynamic';
const Lottie = dynamic(() => import('react-lottie-player'), { ssr: false });
import lottieJson from '@/../public/lotties/500.json';
import { Button } from '@/components';

export default function GlobalError({
  error,
  reset,
}: {
  error: Error & { digest?: string };
  reset: () => void;
}) {
  return (
    <html>
      <body>
        <div className="relative w-[36rem] h-[64rem] pt-[5rem]">
          <section className="w-full mx-auto flex flex-col gap-[1rem]">
            <h3 className="custom-bold-text ml-[1rem] mb-[1rem]">
              세션이 만료되었어요.
            </h3>
            <Lottie
              loop
              animationData={lottieJson}
              play
              style={{ width: 360, height: 360 }}
            />
          </section>

          <div className="w-full absolute p-[1rem] left-0 bottom-0">
            <Button
              mode="ACTIVE"
              label="홈으로 가기"
              onClick={() => window.location.replace('https://mufin.life')}
            />
          </div>
        </div>
      </body>
    </html>
  );
}
