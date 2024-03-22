'use client';

import { useState } from 'react';
import Image from 'next/image';
import { motion } from 'framer-motion';
import { XMarkIcon, ArrowLeftIcon } from '@heroicons/react/24/solid';

interface PayPasswordBoxProps {
  isOpen: boolean;
  onClick?: () => void;
}

const imageArray = [
  'http://localhost:3000/images/icon-5.png',
  'http://localhost:3000/images/icon-4.png',
  'http://localhost:3000/images/icon-1.png',
  'delete',
  'http://localhost:3000/images/icon-6.png',
  'http://localhost:3000/images/icon-2.png',
  'http://localhost:3000/images/icon-0.png',
  'http://localhost:3000/images/icon-8.png',
  'http://localhost:3000/images/icon-7.png',
  'http://localhost:3000/images/icon-3.png',
  'http://localhost:3000/images/icon-9.png',
  'confirm',
];

function EachInputBox({
  isInserted,
  isFocused,
}: {
  isInserted: boolean;
  isFocused: boolean;
}) {
  const insertedString = isInserted ? '*' : '';

  return (
    <div
      className={`w-[4.4rem] h-[5.6rem] rounded-[0.8rem] bg-custom-light-gray flex items-center justify-center custom-bold-text text-custom-black ${
        isFocused && 'animate-border-blink border-[0.2rem]'
      }`}
    >
      {insertedString}
    </div>
  );
}

export default function PayPasswordBox({
  isOpen,
  ...props
}: PayPasswordBoxProps) {
  const [password, setPassword] = useState<number[]>([]);
  const [focusIndex, setFocusIndex] = useState<number>(0);

  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: '100%', opacity: 0 },
  };

  return (
    <motion.section
      variants={container}
      initial="hidden"
      animate={isOpen ? 'show' : 'hidden'}
      transition={{
        type: 'spring',
        mass: 0.5,
        damping: 40,
        stiffness: 400,
      }}
      className={`fixed bottom-0 left-0 right-0 w-full min-w-[36rem] h-[47rem] rounded-t-[1.6rem] bg-custom-white text-custom-black p-[3rem] flex flex-col justify-between`}
      {...props}
    >
      <XMarkIcon className="w-[2.4rem] h-[2.4rem] absolute top-[2rem] right-[2rem] cursor-pointer" />
      <h1 className="custom-bold-text">{'결제 비밀번호를 입력해주세요.'}</h1>

      <div className="mx-auto w-[31rem] h-[5.6rem] flex justify-between">
        {Array.from({ length: 6 }, (_, index) => (
          <EachInputBox
            key={`eachBox-${index}`}
            isInserted={password[index] !== undefined}
            isFocused={index === focusIndex}
          />
        ))}
      </div>

      <div className="mx-auto w-[31rem] h-[23rem] grid grid-cols-4 grid-rows-3">
        {imageArray.map((imageSrc, index) => {
          if (imageSrc !== 'delete' && imageSrc !== 'confirm') {
            return (
              <Image
                key={`button-${index}`}
                width={70}
                height={70}
                src={imageSrc}
                alt={'결제비밀번호 버튼'}
                className="cursor-pointer self-center justify-self-center"
                onClick={() => {
                  if (password.length < 6) {
                    setPassword([...password, index]);
                    setFocusIndex((prev) => prev + 1);
                  }
                }}
              />
            );
          } else if (imageSrc === 'delete') {
            return (
              <button
                key={`button-${index}`}
                onClick={() => {
                  if (password.length > 0) {
                    setPassword(password.slice(0, -1));
                    setFocusIndex((prev) => prev - 1);
                  }
                }}
                className="cursor-pointer self-center justify-self-center w-[7rem] h-[7rem] flex items-center justify-center bg-custom-red-with-opacity rounded-[0.8rem] text-custom-black"
              >
                <ArrowLeftIcon className="w-[2.4rem] h-[2rem]" />
              </button>
            );
          } else if (imageSrc === 'confirm') {
            return (
              <button
                key={`button-${index}`}
                className={`cursor-pointer self-center justify-self-center w-[7rem] h-[7rem] flex items-center justify-center ${
                  password.length === 6
                    ? 'bg-custom-light-purple'
                    : 'bg-custom-blue-with-opacity'
                } rounded-[0.8rem] text-custom-black custom-semibold-text`}
              >
                {'확인'}
              </button>
            );
          }
        })}
      </div>
    </motion.section>
  );
}
