'use client';

import { useState } from 'react';
import { BackButton, Button, GuideText, Header } from '@/components';
import Assesment from './_component/Assesment';

import Lottie from 'react-lottie-player';
import lottieJson from '../../../../../../public/lotties/assesment.json';

export default function Page() {
  const [state, setState] = useState<'START' | 'IN_PROGRESS'>('START');
  return (
    <>
      {state == 'START' ? (
        <>
          <Header>
            <BackButton label="대출 심사" />
          </Header>
          <div className="px-[1.2rem] pt-[0.4rem]">
            <div className="flex flex-col gap-[1rem]">
              <GuideText text="아이에게 일정 금액을 대출해 줄 것인지 심사해요" />
              <Lottie
                loop
                animationData={lottieJson}
                play
                style={{ width: 300, height: 300 }}
              />
              <div className="px-[2rem] flex flex-col gap-[2rem] custom-medium-text text-custom-black">
                <p>
                  아이는 이미 챗봇과 일정 금액이 필요한 이유에 대해 대화를 나눈
                  상태에요. 대화를 보고 아이에게 정말로 필요한 돈인지
                  판단해주세요.
                </p>
                <p>
                  대출을 승인하시면, 아이는 빌린 돈을 일정 기간동안 상환을 하게
                  됩니다. 이 때, 이자율이 없다는 사실을 명심하세요!
                </p>
              </div>

              <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
                <Button
                  label="심사하기"
                  mode="ACTIVE"
                  onClick={() => setState('IN_PROGRESS')}
                />
              </div>
            </div>
          </div>
        </>
      ) : (
        <Assesment />
      )}
    </>
  );
}
