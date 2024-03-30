import type { Meta, StoryObj } from '@storybook/react';

import Notification from './Notification';

const meta = {
  title: 'Common/Notification',
  component: Notification,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    // index: number;
    // message: string;
    // type: string;
    // submessage?: string;
    index: {
      description: '짝수이면 배경이 푸른색입니다',
    },
    message: {
      description: '알림 메세지입니다.',
    },
    type: {
      description: '알림 타입입니다. 타입별로 아이콘이 다릅니다.',
    },
    submessage: {
      description: 'type이 대출 거절 시 이유입니다',
    },
  },
} satisfies Meta<typeof Notification>;

export default meta;
type Story = StoryObj<typeof meta>;

export const LoanReject: Story = {
  args: {
    index: 2,
    message: '대출 신청이 거절되었어요.',
    type: '대출거절',
    submessage: '뭔 술이야',
  },
};

export const LoanApprove: Story = {
  args: {
    index: 2,
    message: '대출 신청이 거절되었어요.',
    type: '대출승인',
  },
};

export const PinMoney: Story = {
  args: {
    index: 2,
    message: '대출 신청이 거절되었어요.',
    type: '용돈',
  },
};
