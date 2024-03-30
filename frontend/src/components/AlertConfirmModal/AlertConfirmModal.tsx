'use client';

import { motion } from 'framer-motion';
import ConfimButton from './ConfirmButton';
import { useState } from 'react';

interface AlertConfirmProps {
  text: string;
  isOpen?: boolean;
  handleClickOkay: () => void;
  handleClickNo: () => void;
}

export default function AlertConfirm({
  text,
  isOpen = false,
  handleClickOkay,
  handleClickNo,
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
      w-[90%]  h-[13.6rem]
      rounded-[0.8rem] bg-custom-white text-custom-black p-[2rem] 
      flex flex-col justify-between`}
    >
      <p className="custom-semibold-text">{text}</p>
      <div className="flex gap-[1rem] justify-end">
        <ConfimButton label="네" mode={'OKAY'} onClick={handleClickOkay} />
        <ConfimButton label="아니오" mode={'CANCEL'} onClick={onClickNo} />
      </div>
    </motion.section>
  );
}
function useNavigate() {
  throw new Error('Function not implemented.');
}
