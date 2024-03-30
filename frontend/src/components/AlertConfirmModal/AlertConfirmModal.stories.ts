import type { Meta, StoryObj } from '@storybook/react';

import AlertConfirmModal from './AlertConfirmModal';

const meta = {
  title: 'Common/AlertConfirmModal',
  component: AlertConfirmModal,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    text: {
      description: '확인 문구 입니다.',
    },
    handleClickOkay: {
      description: '네를 눌렀을 때 동작하는 함수입니다.',
    },
    isOpen: {
      description: '모달 open 여부입니다. 기본 값은 false 입니다.',
    },
    handleClickNo: {
      description: '아니오를 눌렀을 때 동작하는 함수입니다.',
    },
  },
} satisfies Meta<typeof AlertConfirmModal>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    text: '적금 상품을 만드시겠어요?',
    handleClickOkay: () => console.log('HTML tag clicked'),
    isOpen: true,
  },
};
