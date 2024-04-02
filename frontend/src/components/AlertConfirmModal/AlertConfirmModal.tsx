'use client';

import { motion } from 'framer-motion';
import ConfimButton from './ConfirmButton';
import { ReactNode, useState } from 'react';
import { XMarkIcon } from '@heroicons/react/24/solid';

interface AlertConfirmProps {
  text: string;
  isOpen?: boolean;
  handleClickOkay: () => void;
  handleClickNo: () => void;
  handleClickClose?: () => void;
  mode?: 'DEFAULT' | 'ONLYCLOSE';
  height?: string;
  content?: string;
  closeButtonText?: string;
  isXButtonVisible?: boolean;
  handleXButtonClose?: () => void;
  children?: ReactNode;
}

export default function AlertConfirm({
  text,
  isOpen = false,
  handleClickOkay,
  handleClickNo,
  handleClickClose,
  mode = 'DEFAULT',
  height = 'h-[13.6rem]',
  content,
  closeButtonText = '닫기',
  isXButtonVisible = false,
  handleXButtonClose,
  children,
}: AlertConfirmProps) {
  const [isClose, setIsClose] = useState<boolean>(false);

  const container = {
    show: { y: 0, opacity: 1 },
    hidden: { y: '100%', opacity: 0 },
  };

  const onClickNo = () => {
    setIsClose(true);
    handleClickNo();
  };

  return (
    <motion.section
      variants={container}
      initial="hidden"
      animate={!isClose && isOpen ? 'show' : 'hidden'}
      transition={{
        type: 'spring',
        mass: 0.5,
        damping: 40,
        stiffness: 400,
      }}
      className={`fixed top-[13.9rem]
      w-[90%] ${height}
      rounded-[0.8rem] bg-custom-white text-custom-black p-[2rem] 
      flex flex-col justify-between`}
    >
      {isXButtonVisible && (
        <XMarkIcon
          className="size-[2rem] absolute top-[1rem] right-[1rem] cursor-pointer"
          onClick={handleXButtonClose}
        />
      )}
      <p className="custom-semibold-text text-custom-black">{text}</p>
      <p className="custom-light-text text-custom-black pb-[1.6rem]">
        {content}
      </p>
      {children}
      {mode === 'ONLYCLOSE' ? (
        <div className="flex gap-[1rem] justify-end">
          <ConfimButton
            label={closeButtonText}
            mode={'OKAY'}
            onClick={handleClickClose}
          />
        </div>
      ) : (
        <div className="flex gap-[1rem] justify-end">
          <ConfimButton label="네" mode={'OKAY'} onClick={handleClickOkay} />
          <ConfimButton label="아니오" mode={'CANCEL'} onClick={onClickNo} />
        </div>
      )}
    </motion.section>
  );
}
function useNavigate() {
  throw new Error('Function not implemented.');
}
