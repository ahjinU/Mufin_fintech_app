'use client';

import { useState, useEffect, useRef } from 'react';
import Image from 'next/image';
import { motion } from 'framer-motion';
import { XMarkIcon, ArrowLeftIcon } from '@heroicons/react/24/solid';
import PasswordApis from '@/services/password';
import useUserStore from '@/app/_store/store';
import { AlertConfirm, AlertConfirmModal } from '..';

interface PayPasswordBoxProps {
  handleConfirmButton: () => void;
  handleCloseButton: () => void;
  mode: 'ACCOUNT' | 'CHECK';
  isBoxOpenValue?: boolean;
}

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
  handleConfirmButton,
  handleCloseButton,
  mode,
  ...props
}: PayPasswordBoxProps) {
  const { userData } = useUserStore();
  const [password, setPassword] = useState<number[]>([]);
  const [focusIndex, setFocusIndex] = useState<number>(0);
  const [isBoxOpen, setIsBoxOpen] = useState<boolean>(true);
  const [keyPadImage, setKeyPadImage] = useState<string[]>([]);
  const [wrongCnt, setWrongCnt] = useState<number>(0);
  const [isOpen, setIsOpen] = useState<boolean>(false);
  console.log(password);

  const keypadRef = useRef<boolean>(false);

  const {
    getKeyPadImage,
    checkPayPassword,
    getAccountKeyPadImage,
    setPayPassword,
  } = PasswordApis();

  useEffect(() => {
    !keypadRef.current &&
      (async () => {
        let data;
        if (mode === 'ACCOUNT') {
          data = await getAccountKeyPadImage();
        } else {
          if (userData?.accountNumber)
            data = await getKeyPadImage(userData?.accountNumber);
        }
        const keyPadData = data.data.keypad;
        keyPadData.splice(3, 0, 'delete');
        keyPadData.splice(11, 0, 'confirm');
        setKeyPadImage(keyPadData);
      })();

    return () => {
      keypadRef.current = true;
    };
  }, []);

  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: '100%', opacity: 0 },
  };

  const closeBox = () => {
    setIsBoxOpen(false);
    handleCloseButton();
  };

  const confirmPassword = () => {
    (async () => {
      let data;
      if (mode === 'ACCOUNT') {
        data = await setPayPassword(password);
      } else {
        if (userData?.accountNumber)
          data = await checkPayPassword(userData?.accountNumber, password);
      }
      console.log(data);

      if (mode === 'CHECK') setWrongCnt(data?.data?.incorrectCnt);
      if (data.errorMessage === '키패드 요청 시간이 만료되었습니다.') {
        setIsOpen(true);
      }
      if (
        data.message === '계좌 비밀번호가 일치합니다.' ||
        mode === 'ACCOUNT'
      ) {
        setIsBoxOpen(false);
        handleConfirmButton();
      }
    })();
  };

  return !isOpen ? (
    <motion.section
      variants={container}
      initial="hidden"
      animate={isBoxOpen ? 'show' : 'hidden'}
      transition={{
        type: 'spring',
        mass: 0.5,
        damping: 40,
        stiffness: 400,
      }}
      className={`fixed bottom-0 left-0 right-0 w-full min-w-[36rem] h-[47rem] rounded-t-[1.6rem] bg-custom-white text-custom-black p-[2rem] flex flex-col justify-between`}
      {...props}
    >
      <XMarkIcon
        className="w-[2.4rem] h-[2.4rem] absolute top-[2rem] right-[2rem] cursor-pointer"
        onClick={closeBox}
      />
      <h1 className="custom-bold-text">{'계좌 비밀번호 입력'}</h1>

      <div className="mx-auto w-[30rem] h-[5.6rem] flex flex-col">
        <div className="flex justify-between">
          {Array.from({ length: 6 }, (_, index) => (
            <EachInputBox
              key={`eachBox-${index}`}
              isInserted={password[index] !== undefined}
              isFocused={index === focusIndex}
            />
          ))}
        </div>
        {mode === 'CHECK' && (
          <p className="text-custom-red custom-light-text self-end">
            계좌 비밀번호 틀린 횟수({wrongCnt} / 5)
          </p>
        )}
      </div>

      <div className="mx-auto w-[31rem] h-[23rem] grid grid-cols-4 grid-rows-3">
        {keyPadImage.map((imageSrc, index) => {
          if (imageSrc !== 'delete' && imageSrc !== 'confirm') {
            return (
              <Image
                key={`button-${index}`}
                width={70}
                height={70}
                src={imageSrc}
                alt={'계좌 비밀번호 버튼'}
                className="cursor-pointer self-center justify-self-center rounded-[0.8rem]"
                onClick={() => {
                  if (password.length < 6) {
                    setPassword([...password, index > 3 ? index - 1 : index]);
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
                onClick={password.length === 6 ? confirmPassword : undefined}
              >
                {'확인'}
              </button>
            );
          }
        })}
      </div>
    </motion.section>
  ) : (
    <div className="flex items-center justify-center">
      <AlertConfirmModal
        mode="ONLYCLOSE"
        text={'요청 시간이 만료되었어요'}
        handleClickOkay={function (): void {
          throw new Error('Function not implemented.');
        }}
        handleClickNo={function (): void {
          throw new Error('Function not implemented.');
        }}
        handleClickClose={() => closeBox()}
        isOpen={isOpen}
      />
    </div>
  );
}
